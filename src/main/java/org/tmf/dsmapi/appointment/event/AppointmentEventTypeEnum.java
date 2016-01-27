package org.tmf.dsmapi.appointment.event;

public enum AppointmentEventTypeEnum {

    AppointmentCreationNotification("AppointmentCreationNotification"),
    AppointmentUpdateNotification("AppointmentUpdateNotification"),
    AppointmentDeletionNotification("AppointmentDeletionNotification"),
    AppointmentValueChangeNotification("AppointmentValueChangeNotification"),
    AppointmentStatusChangedNotification("AppointmentStatusChangedNotification");

    private String text;

    AppointmentEventTypeEnum(String text) {
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
    public static AppointmentEventTypeEnum fromString(String text) {
        if (text != null) {
            for (AppointmentEventTypeEnum b : AppointmentEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }

}