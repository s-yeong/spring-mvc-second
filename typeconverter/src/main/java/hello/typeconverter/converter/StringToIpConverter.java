package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        // 127.0.0.1:8080 문자 -> IpPort 객체
        log.info("convert source={}", source);
        String[] split = source.split(":");
        String ip = split[0];   // 127.0.0.1
        int port = Integer.parseInt(split[1]);  //8080

        return new IpPort(ip, port);
    }
}
