package scribles.illumio.parser;

import scribles.illumio.core.Rule;
import scribles.illumio.core.Range;

public class Parser {

    public Parser() {
        //add any initialisation if needed later
    }
    public Rule getRule(String direction, String protocol, String portInfo, String ipInfo){
        Range<Integer> portRange = null;
        if(portInfo.contains("-")){
            String tempPortInfo[] = portInfo.split("-");
            portRange = new Range<>(Integer.parseInt(tempPortInfo[0]),Integer.parseInt(tempPortInfo[1]));
        }else{
            portRange = new Range<>(Integer.parseInt(portInfo));
        }

        Range<Long> ipRange = null;
        if(ipInfo.contains("-")){
            String tempIpInfo[] = ipInfo.split("-");
            ipRange = new Range<>(getIpToLong(tempIpInfo[0]),getIpToLong(tempIpInfo[1]));
        }else{
            ipRange = new Range<>(getIpToLong(ipInfo));
        }

        return new Rule(direction,protocol,portRange,ipRange);
    }
    public Rule getRule(String line){
        //validate line
        validate(line);
        String temp[] = line.split(",");

        String direction = temp[0];

        String protocol = temp[1];

        String portInfo = temp[2];

        String ipInfo = temp[3];

        return getRule(direction,protocol,portInfo,ipInfo);
    }

    private static Long getIpToLong(String ipInfo) {
        String temp[] = ipInfo.split("\\.");
        long power = 1;
        long value = 0;
        for(int i = temp.length - 1;i>=0;i--){
            value += Long.parseLong(temp[i])*power;
            power *= 256;
        }
        return value;
    }

    private void validate(String line) {
        if(line == null || line.length() == 0){
            throw new RuntimeException("Empty line encountered.");
        }
        String temp[] = line.split(",");
        if(temp.length!=4){
            throw new RuntimeException("Incorrect number of entries in line.");
        }

        //TODO: add other validation. But, question says code is valid, so ignore right now.

    }


}
