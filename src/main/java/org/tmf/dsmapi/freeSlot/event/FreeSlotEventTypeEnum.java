package org.tmf.dsmapi.freeSlot.event;

public enum FreeSlotEventTypeEnum {

    FreeSlotCreationNotification("FreeSlotCreationNotification"),
    FreeSlotUpdateNotification("FreeSlotUpdateNotification"),
    FreeSlotDeletionNotification("FreeSlotDeletionNotification"),
    FreeSlotValueChangeNotification("FreeSlotValueChangeNotification"),
    FreeSlotStatusChangedNotification("FreeSlotStatusChangedNotification");

    private String text;

    FreeSlotEventTypeEnum(String text) {
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
    public static FreeSlotEventTypeEnum fromString(String text) {
        if (text != null) {
            for (FreeSlotEventTypeEnum b : FreeSlotEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }

}