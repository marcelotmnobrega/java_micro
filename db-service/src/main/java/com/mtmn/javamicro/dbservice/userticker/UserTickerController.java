package com.mtmn.javamicro.dbservice.userticker;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db/userticker")
public class UserTickerController {

    private UserTickerRepository userTickerRepository;

    public UserTickerController(UserTickerRepository userTickerRepository) {
        this.userTickerRepository = userTickerRepository;
    }

    @RequestMapping("/{username}")
    public List<String> getTickers(@PathVariable("username") final String username){
        return getTickersByUsername(username);
    }

    @PostMapping("/add")
    public List<String> addTickersForUser(@RequestBody final UserTickerAddRequestBody userTickerAddRequestBody){

        final String username = userTickerAddRequestBody.getUsername();
        userTickerAddRequestBody
                .getTickers()
                .stream()
                .map(quoteStr -> new UserTicker(username, quoteStr))
                .forEach(userTickerRepository::save);
        return getTickersByUsername(username);
    }

    @DeleteMapping("/delete/{username}")
    @Transactional
    public List<UserTicker> deleteUserTickersByUsername(@PathVariable("username") final String username){
        userTickerRepository.deleteByUsername(username);
        return userTickerRepository.findByUsername(username);
    }


    private List<String> getTickersByUsername(@PathVariable("username") String username) {
        return userTickerRepository
                .findByUsername(username)
                .stream()
                .map(UserTicker::getTicker)
                .collect(Collectors.toList());
    }

}
