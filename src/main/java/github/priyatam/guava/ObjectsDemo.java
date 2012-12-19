package github.priyatam.guava;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import sun.security.util.PolicyUtil;

import java.io.Serializable;
import java.util.*;

// Sample class: Policy
class ObjectsDemo {

    public static void main(String args[]) {
        utils();
    }

    static void utils() {
        Policy p1 = new Policy("123","gaeco", null, null, "local500", 1200, 2);
        Policy p2 = new Policy("123","gaeco", null, null, "agency-123", 1500, 2);
        Policy p3 = new Policy("abc","gaeco", null, null, "test", 1500, 2);

        // toString
        System.out.println(p1);

        // equals
        System.out.println(Objects.equal(p1, p2));
        List list = Arrays.asList(p1, p2, p3);
        System.out.println("Before Sort: "+ list);
        Collections.sort(list);
        System.out.println("Before Sort:" + list);
    }

    static class Policy implements Comparable<Policy>, Serializable {

        private static final long serialVersionUID = 2144310940122088721L;

        private final String policyNum;
        private final Date effectiveDate;
        private final Date expiryDate;
        private final String company;
        private final String agency;
        private final Integer quote;
        private final Integer term;

        private final List<String> drivers = new ArrayList<String>(0);
        private final List<String> vehicles = new ArrayList<String>(0);

        public Policy(String policyNum, String company, Date effectiveDate, Date expiryDate, String agency, Integer quote,
                           Integer term) {
            this.policyNum = policyNum;
            this.company = company;
            this.effectiveDate = effectiveDate;
            this.expiryDate = expiryDate;
            this.agency = agency;
            this.quote = quote;
            this.term = term;
       }

        public void addDriver(String driver) {
            this.drivers.add(driver);
        }

        public void addVehicle(String vehicle) {
            this.drivers.add(vehicle);
        }

        public String getPolicyNum() {
            return policyNum;
        }

        public String getCompany() {
            return company;
        }

        public String getAgency() {
            return agency;
        }

        public Date getEffectiveDate() {
            return effectiveDate;
        }

       public Integer getQuote() {
            return quote;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public Integer getTerm() {
            return term;
        }

        public List<String> getDrivers() {
            return drivers;
        }

        public List<String> getVehicles() {
            return vehicles;
        }

        public String getSource() {
            return Objects.firstNonNull(company, agency);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("policyNum", policyNum).add("effectiveDate", effectiveDate)
                    .add("agency", agency).toString();
        }

        @Override
        public int compareTo(Policy that) {
            return ComparisonChain.start().compare(this.policyNum, that.policyNum).compare(this.agency, that.agency , Ordering.natural().nullsLast()).result();
        }

        @Override
        public boolean equals(Object obj) {
            Policy that = (Policy) obj;
            return Objects.equal(this.policyNum, that.policyNum);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(policyNum);
        }
    }

}