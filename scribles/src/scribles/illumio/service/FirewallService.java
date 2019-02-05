package scribles.illumio.service;

import scribles.illumio.core.Rule;
import scribles.illumio.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FirewallService {
    //TODO: add spring to autowire and remove management
    private final Parser parser;
    public FirewallService(){
        //initialisations, if any
        this.parser = new Parser();
    }

    public List<Rule> getRules(String firewallFileName) throws IOException {
        BufferedReader fileIn = new BufferedReader(new FileReader(firewallFileName));
        String line;
        List<Rule> rules = new ArrayList<>();
        while((line = fileIn.readLine())!=null){
            rules.add(parser.getRule(line));
        }
        return rules;
    }
}
