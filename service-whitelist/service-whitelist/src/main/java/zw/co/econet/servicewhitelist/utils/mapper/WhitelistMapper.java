package zw.co.econet.servicewhitelist.utils.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import zw.co.econet.servicewhitelist.domain.Whitelist;
import zw.co.econet.servicewhitelist.utils.dto.WhitelistDto;

@Mapper(componentModel = "spring")
public abstract class WhitelistMapper {

    public abstract WhitelistDto map(Whitelist whitelist);

    public abstract Whitelist map(WhitelistDto whitelistDto);

    public abstract List<WhitelistDto> map(List<Whitelist> whitelistList);

}
