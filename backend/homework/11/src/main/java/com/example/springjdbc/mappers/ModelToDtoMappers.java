package com.example.springjdbc.mappers;

import com.example.springjdbc.dto.ShiftTypeResponseDTO;
import com.example.springjdbc.dto.ShiftUserResponseDTO;
import com.example.springjdbc.dto.ShiftsResponseDTO;
import com.example.springjdbc.dto.UsersResponseDTO;
import com.example.springjdbc.model.ShiftType;
import com.example.springjdbc.model.ShiftUser;
import com.example.springjdbc.model.Shifts;
import com.example.springjdbc.model.Users;
import org.springframework.stereotype.Component;

@Component
public class ModelToDtoMappers {


    public UsersResponseDTO toResponseDTO(Users users) {
        return new UsersResponseDTO(
                users.getUsername(),
                users.isLoggedIn(),
                users.getCreatedBy(),
                users.getUpdatedBy(),
                users.getTimeZone(),
                users.getTenantId(),
                users.getCreatedAt(),
                users.getUpdateAt()
        );
    }
    public ShiftsResponseDTO toResponseDTO(Shifts shifts) {
        return new ShiftsResponseDTO(
                shifts.getShiftTypeId(),
                shifts.getName(),
                shifts.getDateStart(),
                shifts.getDateEnd(),
                shifts.getTimeStart(),
                shifts.getTimeEnd(),
                shifts.getCreatedBy(),
                shifts.getUpdatedBy(),
                shifts.getTimeZone(),
                shifts.getTenantId(),
                shifts.getCreatedAt(),
                shifts.getUpdateAt()
        );
    }
    public ShiftTypeResponseDTO toResponseDTO(ShiftType shiftType) {
        return new ShiftTypeResponseDTO(
                shiftType.getUqName(),
                shiftType.getDescription(),
                shiftType.isActive(),
                shiftType.getCreatedBy(),
                shiftType.getUpdatedBy(),
                shiftType.getTimeZone(),
                shiftType.getTenantId(),
                shiftType.getCreatedAt(),
                shiftType.getUpdateAt()
        );
    }

    public ShiftUserResponseDTO toResponseDTO(ShiftUser shiftUser) {
        return new ShiftUserResponseDTO(
                shiftUser.getShiftId(),
                shiftUser.getUserId(),
                shiftUser.getTenantId(),
                shiftUser.getCreatedBy(),
                shiftUser.getUpdatedBy(),
                shiftUser.getCreatedAt(),
                shiftUser.getUpdateAt()
        );
    }
}
