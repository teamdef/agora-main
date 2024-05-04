package def.agoramain;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        security = {
                @SecurityRequirement(name = "Authorization"),
        }
)
@SecuritySchemes({
        @SecurityScheme(name = "Authorization",
                type = SecuritySchemeType.APIKEY,
                description = "Bearer token",
                in = SecuritySchemeIn.HEADER,
                paramName = "Authorization"),
})
@Configuration
public class Config {
}
