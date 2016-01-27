package org.tmf.dsmapi.appointment;

import org.tmf.dsmapi.commons.workflow.StateModelBase;
import org.tmf.dsmapi.appointment.model.AppointmentStatusEnum;

public class StateModelImpl extends StateModelBase<AppointmentStatusEnum> {
    
    /**
     *
     */
    public StateModelImpl() {
        super(AppointmentStatusEnum.class);
    }    

    /**
     *
     */
    @Override
    protected void draw() {
        // First
        fromFirst(AppointmentStatusEnum.INITIALISED).to(AppointmentStatusEnum.VALIDATED,
                AppointmentStatusEnum.CANCELLED);

        // Somewhere
        from(AppointmentStatusEnum.VALIDATED).to(AppointmentStatusEnum.MISSED,
                AppointmentStatusEnum.ATTENTED, AppointmentStatusEnum.CANCELLED);
        from(AppointmentStatusEnum.CANCELLED).to(AppointmentStatusEnum.COMPLETED);

        // Final
        from(AppointmentStatusEnum.COMPLETED);
    }
}
