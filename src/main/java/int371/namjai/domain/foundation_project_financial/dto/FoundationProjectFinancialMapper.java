package int371.namjai.domain.foundation_project_financial.dto;

import int371.namjai.domain.foundation_project_financial.FoundationProjectFinancial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoundationProjectFinancialMapper {
    FoundationProjectFinancialMapper INSTANCE = Mappers.getMapper(FoundationProjectFinancialMapper.class);

    @Mappings({
            @Mapping(target = "fdnProjectUUID", source = "foundationProject.fdnProjectUUid"),
            @Mapping(target = "financialPlanUUID", source = "fdnProjectFinancialUUID"),
            @Mapping(target = "detail", source = "detail"),
            @Mapping(target = "quantity", source = "quantity"),
            @Mapping(target = "amount", source = "amount"),
    })
    FoundationProjectFinancialFormDTO toFoundationProjectFinancialFormDto(FoundationProjectFinancial foundationProjectFinancial);

    List<FoundationProjectFinancialFormDTO> toFoundationProjectFinancialFormDtoList(List<FoundationProjectFinancial> foundationProjectFinancialList);
}
