package scribles.illumio.service;

import scribles.illumio.core.Rule;
import scribles.illumio.core.Range;
import scribles.illumio.parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lookup {
    private final List<Rule> rules;
    private final Map<RuleKey,List<RuleValue>> map;
    private final Parser parser;
    public Lookup(List<Rule> rules) {
        this.rules = rules;
        this.map = new HashMap<>();
        this.parser = new Parser();//TODO: use spring autowire
        loadMap();
    }

    private void loadMap(){
        for(Rule rule : this.rules){
            RuleKey key = new RuleKey(rule.getDirection(),rule.getProtocol());
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(new RuleValue(rule.getPortRange(),rule.getIpRange()));
        }
    }

    public boolean contains(String direction, String protocol, Integer port, String ip) {
        Rule rule = parser.getRule(direction,protocol,port+"",ip);
        RuleKey key = new RuleKey(rule.getDirection(),rule.getProtocol());
        List<RuleValue> ruleValues = map.get(key);
        if(ruleValues!=null){
            for(RuleValue ruleValue : ruleValues){
                Integer portValue = (Integer)rule.getPortRange().getStart();
                Long ipValue = (Long)rule.getIpRange().getStart();
                if(match(ruleValue,portValue, ipValue)){
                    return true;
                }
            }

        }
        return false;
    }

    private boolean match(RuleValue ruleValue, Integer port, Long ip) {
        Range<Integer> portRange = ruleValue.getPortRange();
        Range<Long> ipRange = ruleValue.getIpRange();

        Integer portStart = portRange.getStart();
        Integer portEnd = (portRange.hasEnd()?portRange.getEnd():portStart);

        Long ipStart = ipRange.getStart();
        Long ipEnd = (ipRange.hasEnd()?ipRange.getEnd():ipStart);

        return portStart<= port && port <= portEnd && ipStart<= ip && ip<= ipEnd;
    }
}
