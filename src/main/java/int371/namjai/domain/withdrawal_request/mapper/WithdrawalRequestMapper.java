package int371.namjai.domain.withdrawal_request.mapper;

import int371.namjai.domain.withdrawal_request.WithdrawalRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WithdrawalRequestMapper {
    WithdrawalRequestMapper INSTANCE = Mappers.getMapper(WithdrawalRequestMapper.class);

    //    @Mapping(target = "volunteerProjectUUID", source = "volunteerEnrolled.volunteerProjectUUID"),
    @Mappings({
            @Mapping(target = "withdrawalUUID", source = "withdrawalUUID"),
            @Mapping(target = "foundationProjectName", source = "withdrawalRequest.foundationProject.fdnProjectName"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "totalAmount", source = "withdrawalRequest.foundationProject.received"),
            @Mapping(target = "createDate", source = "createDate"),
            @Mapping(target = "approveDate", source = "approveDate")
    })
    WithdrawalRequestApproveShortDTO toWithdrawalRequestApproveShortDto(WithdrawalRequest withdrawalRequest);

    List<WithdrawalRequestApproveShortDTO> toWithdrawalRequestApproveShortDtoList(List<WithdrawalRequest> withdrawalRequestList);

    @Mappings({
            @Mapping(target = "withdrawalUUID", source = "withdrawalUUID"),
            @Mapping(target = "foundationProjectUUID", source = "withdrawalRequest.foundationProject.fdnProjectUUid"),
            @Mapping(target = "foundationProjectName", source = "withdrawalRequest.foundationProject.fdnProjectName"),
            @Mapping(target = "totalAmount", source = "withdrawalRequest.foundationProject.received"),
            @Mapping(target = "fdnUUID", source = "withdrawalRequest.foundationProject.foundation.fdnUUid"),
            @Mapping(target = "fdnName", source = "withdrawalRequest.foundationProject.foundation.fdnName"),
            @Mapping(target = "fdnEmail", source = "withdrawalRequest.foundationProject.foundation.email"),
            @Mapping(target = "bankAccountUUID", source = "withdrawalRequest.bankAccount.bankAccountUUID"),
            @Mapping(target = "bankAccountName", source = "withdrawalRequest.bankAccount.accountName"),
            @Mapping(target = "bankAccountBrand", source = "withdrawalRequest.bankAccount.bankBrand"),
            @Mapping(target = "bankNumber", source = "withdrawalRequest.bankAccount.bankNumber")
    })
    WithdrawalRequestApproveDetailDTO toWithdrawalRequestApproveDetailDto(WithdrawalRequest withdrawalRequest);
}
