package scribles.illumio.core;

public class Rule {
    private final String direction;
    private final String protocol;
    private final Range<Integer> portRange;
    private final Range<Long> ipRange;

    public Rule(String direction, String protocol, Range<Integer> portRange, Range<Long> ipRange) {
        this.direction = direction;
        this.protocol = protocol;
        this.portRange = portRange;
        this.ipRange = ipRange;
    }

    public String getDirection() {
        return direction;
    }

    public String getProtocol() {
        return protocol;
    }

    public Range getPortRange() {
        return portRange;
    }

    public Range getIpRange() {
        return ipRange;
    }
}
