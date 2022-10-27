package int371.namjai.domain.bank_account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    //    @Mapping(target = "volunteerProjectUUID", source = "volunteerEnrolled.volunteerProjectUUID"),
    @Mappings({
            @Mapping(target = "value", source = "bankAccountUUID"),
            @Mapping(target = "bankBrand", source = "bankBrand"),
            @Mapping(target = "bankNumber", source = "bankNumber"),
            @Mapping(target = "accountName", source = "accountName")
    })
    BankAccountDTO toBankAccountDTO(BankAccount bankAccount);

    List<BankAccountDTO> toBankAccountDTOList(List<BankAccount> bankAccounts);
}
