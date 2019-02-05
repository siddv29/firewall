package scribles.illumio.service;

import scribles.illumio.core.Range;

import java.util.Objects;

class RuleValue {
    private final Range<Integer> portRange;
    private final Range<Long> ipRange;

    public RuleValue(Range<Integer> portRange, Range<Long> ipRange) {
        this.portRange = portRange;
        this.ipRange = ipRange;
    }

    public Range<Integer> getPortRange() {
        return portRange;
    }

    public Range<Long> getIpRange() {
        return ipRange;
    }

    @Override
    public int hashCode() {
        return Objects.hash(portRange, ipRange);
    }
}
