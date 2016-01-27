package org.tmf.dsmapi.schedule.event;

public enum ScheduleEventTypeEnum {

    ScheduleCreationNotification("ScheduleCreationNotification"),
    ScheduleUpdateNotification("ScheduleUpdateNotification"),
    ScheduleDeletionNotification("ScheduleDeletionNotification"),
    ScheduleValueChangeNotification("ScheduleValueChangeNotification"),
    ScheduleStatusChangedNotification("ScheduleStatusChangedNotification");

    private String text;

    ScheduleEventTypeEnum(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return this.text;
    }

    /**
     *
     * @param text
     * @return
     */
    public static ScheduleEventTypeEnum fromString(String text) {
        if (text != null) {
            for (ScheduleEventTypeEnum b : ScheduleEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }

}