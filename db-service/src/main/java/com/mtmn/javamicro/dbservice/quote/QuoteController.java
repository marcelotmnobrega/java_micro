package com.mtmn.javamicro.dbservice.quote;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class QuoteController {

    private QuoteRepository quoteRepository;

    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @RequestMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username){
        return getQuotesByUsername(username);
    }

    @PostMapping("/add")
    public List<String> addQuotesForUser(@RequestBody final QuoteAddRequestBody quoteAddRequestBody){

        final String username = quoteAddRequestBody.getUsername();
        quoteAddRequestBody
                .getQuotes()
                .stream()
                .map(quoteStr -> new Quote(username, quoteStr))
                .forEach(quoteRepository::save);
        return getQuotesByUsername(username);
    }

    @DeleteMapping("/delete/{username}")
    @Transactional
    public List<Quote> deleteQuotesFromUser(@PathVariable("username") final String username){
        quoteRepository.deleteByUsername(username);
        return quoteRepository.findByUsername(username);
    }


    private List<String> getQuotesByUsername(@PathVariable("username") String username) {
        return quoteRepository
                .findByUsername(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

}
