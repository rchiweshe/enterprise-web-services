package zw.co.econet.enterprise.web.services.common.utils.enums;

public enum RecordStatus {

    DELETED("INACTIVE"), ACTIVE("ACTIVE");

    RecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    private String recordStatus;

    public String getRecordStatus() {
        return recordStatus;
    }
}
