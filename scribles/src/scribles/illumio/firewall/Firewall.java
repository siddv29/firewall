package scribles.illumio.firewall;


import scribles.illumio.core.Rule;
import scribles.illumio.service.FirewallService;
import scribles.illumio.service.Lookup;

import java.io.IOException;
import java.util.List;

public class Firewall {
    private final String fileName;
    private final List<Rule> rules;
    private final FirewallService firewallService;
    private final Lookup lookup;

    public Firewall(String fileName) throws IOException {
        this.fileName = fileName;
        this.firewallService = new FirewallService();


        //load rules at instantiation
        this.rules = firewallService.getRules(fileName);

        //parse rules also
        this.lookup = new Lookup(rules);

        //all initializations done

        //this could be a bottleneck of doing it in constructor time
        //TODO: try to make it asynchronous
    }
    public boolean acceptPacket(String direction, String protocol, int port, String ip){
        return lookup.contains(direction,protocol,port,ip);
    }

    public static void main(String[] args) throws Exception{
        System.out.println("Testing");
        Firewall fw = new Firewall("C:\\Users\\siddharth verma\\IdeaProjects\\scribles\\src\\scribles\\illumio\\firewall\\sample.txt");
        System.out.println(fw.acceptPacket("inbound", "tcp", 80, "192.168.1.2"));
        System.out.println(fw.acceptPacket("inbound", "udp", 53, "192.168.2.1"));
        System.out.println(fw.acceptPacket("outbound", "tcp", 10234, "192.168.10.11"));
        System.out.println(fw.acceptPacket("inbound", "tcp", 81, "192.168.1.2"));
        System.out.println(fw.acceptPacket("inbound", "udp", 24, "52.12.48.92"));
    }


}
