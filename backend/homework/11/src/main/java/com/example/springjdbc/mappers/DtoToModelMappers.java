package com.example.springjdbc.mappers;

import com.example.springjdbc.dto.*;
import com.example.springjdbc.model.ShiftType;
import com.example.springjdbc.model.ShiftUser;
import com.example.springjdbc.model.Shifts;
import com.example.springjdbc.model.Users;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class DtoToModelMappers {
    public ShiftUser convertShiftUserDtoToShiftUser(ShiftUserRequestDTO shiftUserRequestDTO) {
        UUID uuid=UUID.randomUUID();
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();

        return new ShiftUser(uuid,
                shiftUserRequestDTO.getShiftId(),
                shiftUserRequestDTO.getUserId(),
                shiftUserRequestDTO.getTenantId(),
                shiftUserRequestDTO.getCreatedBy(),
                shiftUserRequestDTO.getUpdatedBy(),
                createdAt,updatedAt);
    }
    public ShiftType convertShiftTypeDtoToShiftType(ShiftTypeRequestDTO shiftTypeRequestDTO) {
        UUID uuid=UUID.randomUUID();
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();
        return new ShiftType(uuid,
                shiftTypeRequestDTO.getUqName(),
                shiftTypeRequestDTO.getDescription(),
                shiftTypeRequestDTO.isActive(),
                shiftTypeRequestDTO.getCreatedBy(),
                shiftTypeRequestDTO.getUpdatedBy(),
                shiftTypeRequestDTO.getTimeZone(),
                shiftTypeRequestDTO.getTenantId(),
                createdAt,updatedAt);
    }
    public Shifts convertShiftDtoToShift(ShiftsRequestDTO shiftsRequestDTO) {
        UUID uuid=UUID.randomUUID();
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();
        return new Shifts(uuid,
                shiftsRequestDTO.getShiftTypeId(),
                shiftsRequestDTO.getName(),
                shiftsRequestDTO.getDateStart(),
                shiftsRequestDTO.getDateEnd(),
                shiftsRequestDTO.getTimeStart(),
                shiftsRequestDTO.getTimeEnd(),
                shiftsRequestDTO.getCreatedBy(),
                shiftsRequestDTO.getUpdatedBy(),
                shiftsRequestDTO.getTimeZone(),
                shiftsRequestDTO.getTenantId(),
                createdAt,updatedAt);
    }

    public Users convertUserDtoToUser (UsersRequestDTO usersRequestDTO) {
        UUID uuid=UUID.randomUUID();
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();
        return new Users(uuid,
                usersRequestDTO.getUsername(),
                usersRequestDTO.isLoggedIn(),
                usersRequestDTO.getCreatedBy(),
                usersRequestDTO.getUpdatedBy(),
                usersRequestDTO.getTimeZone(),
                usersRequestDTO.getTenantId(),
                createdAt,updatedAt);
    }

    public void convertAllDetailsDtoToModel(Users users, Shifts shifts, ShiftType shiftType, ShiftUser shiftUser, AllDetailsRequestDTO allDetailsRequestDTO) {
        UUID userUUID=UUID.randomUUID();
        UUID shiftUUID=UUID.randomUUID();
        UUID shiftTypeUUID=UUID.randomUUID();
        UUID shiftUserUUID=UUID.randomUUID();
        Instant createdAt = Instant.now();
        Instant updatedAt = Instant.now();

        users.setId(userUUID);
        users.setUsername(allDetailsRequestDTO.getUsername());
        users.setLoggedIn(allDetailsRequestDTO.isLoggedIn());
        users.setCreatedBy(allDetailsRequestDTO.getCreatedBy());
        users.setUpdatedBy(allDetailsRequestDTO.getUpdatedBy());
        users.setTimeZone(allDetailsRequestDTO.getTimeZone());
        users.setTenantId(allDetailsRequestDTO.getTenantId());
        users.setUpdateAt(updatedAt);
        users.setCreatedAt(createdAt);

        shifts.setId(shiftUUID);
        shifts.setShiftTypeId(shiftTypeUUID);
        shifts.setName(allDetailsRequestDTO.getName());
        shifts.setDateStart(allDetailsRequestDTO.getDateStart());
        shifts.setDateEnd(allDetailsRequestDTO.getDateEnd());
        shifts.setTimeStart(allDetailsRequestDTO.getTimeStart());
        shifts.setTimeEnd(allDetailsRequestDTO.getTimeEnd());
        shifts.setCreatedBy(allDetailsRequestDTO.getCreatedBy());
        shifts.setUpdatedBy(allDetailsRequestDTO.getUpdatedBy());
        shifts.setTimeZone(allDetailsRequestDTO.getTimeZone());
        shifts.setTenantId(allDetailsRequestDTO.getTenantId());
        shifts.setCreatedAt(createdAt);
        shifts.setUpdateAt(updatedAt);

        shiftType.setId(shiftTypeUUID);
        shiftType.setUqName(allDetailsRequestDTO.getUqName());
        shiftType.setDescription(allDetailsRequestDTO.getDescription());
        shiftType.setActive(allDetailsRequestDTO.isActive());
        shiftType.setCreatedBy(allDetailsRequestDTO.getCreatedBy());
        shiftType.setUpdatedBy(allDetailsRequestDTO.getUpdatedBy());
        shiftType.setTimeZone(allDetailsRequestDTO.getTimeZone());
        shiftType.setTenantId(allDetailsRequestDTO.getTenantId());
        shiftType.setCreatedAt(createdAt);
        shiftType.setUpdateAt(updatedAt);

        shiftUser.setId(shiftUserUUID);
        shiftUser.setShiftId(shiftUUID);
        shiftUser.setUserId(userUUID);
        shiftUser.setTenantId(allDetailsRequestDTO.getTenantId());
        shiftUser.setCreatedBy(allDetailsRequestDTO.getCreatedBy());
        shiftUser.setUpdatedBy(allDetailsRequestDTO.getUpdatedBy());
        shiftUser.setCreatedAt(createdAt);
        shiftUser.setUpdateAt(updatedAt);
    }
}
