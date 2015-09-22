package com.pmrodrigues.pagamentos.akatus.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentMethod implements Entity {
    public class Flag {
        private String code;
        private String description;
        private Integer installments;

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public Integer getInstallments() {
            return installments;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setInstallments(Integer installments) {
            this.installments = installments;
        }
    }

    private String code;
    private String description;
    private List<Flag> flags = new ArrayList<Flag>();
    private Integer installments;

    public void addPaymentMethodFlag(String code, String description,
                                     Integer installments) {
        final Flag flag = new Flag();
        flag.setCode(code);
        flag.setDescription(description);
        flag.setInstallments(installments);

        flags.add(flag);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Iterator<Flag> getFlagIterator() {
        return flags.iterator();
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}