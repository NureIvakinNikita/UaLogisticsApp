package nikita.ivakin.apzpzpi215ivakinnikitatask2.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.UserAlreadyExistsException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.token.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.user.User;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.user.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BrigadeCommanderService brigadeCommanderService;
    private final BattalionCommanderService battalionCommanderService;
    private final CompanyCommanderService companyCommanderService;
    private final PlatCommanderService platCommanderService;
    private final LogisticCommanderService logisticCommanderService;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with such email already exists.");
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .passportNumber(request.getPassportNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .rank(request.getRank())
                .post(request.getPost())
                .build();
        String role = request.getRole().name();
        var savedUser = repository.save(user);
        switch(role) {
            case "BRIGADE_COMMANDER":
                BrigadeCommander brigCommander = (BrigadeCommander) BrigadeCommander.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .secondName(request.getSecondName())
                        .age(request.getAge())
                        .passportNumber(request.getPassportNumber())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .post(request.getPost())
                        .rank(request.getRank())
                        .role(request.getRole())
                        .build();
                brigadeCommanderService.save(brigCommander);
                break;
            case "BATTALION_COMMANDER":
                BattalionCommander battalionCommander = (BattalionCommander) BattalionCommander.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .secondName(request.getSecondName())
                        .age(request.getAge())
                        .passportNumber(request.getPassportNumber())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .post(request.getPost())
                        .rank(request.getRank())
                        .role(request.getRole())
                        .build();
                battalionCommanderService.save(battalionCommander);
                break;
            case "COMPANY_COMMANDER":
                CompanyCommander companyCommander = (CompanyCommander) CompanyCommander.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .secondName(request.getSecondName())
                        .age(request.getAge())
                        .passportNumber(request.getPassportNumber())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .post(request.getPost())
                        .rank(request.getRank())
                        .role(request.getRole())
                        .build();
                companyCommanderService.save(companyCommander);
                break;
            case "PLAT_COMMANDER":
                PlatCommander platCommander = (PlatCommander) PlatCommander.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .secondName(request.getSecondName())
                        .age(request.getAge())
                        .passportNumber(request.getPassportNumber())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .post(request.getPost())
                        .rank(request.getRank())
                        .role(request.getRole())
                        .build();
                platCommanderService.save(platCommander);
                break;
            case "LOGISTIC_COMMANDER":
                LogisticCommander logisticCommander = (LogisticCommander) LogisticCommander.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .secondName(request.getSecondName())
                        .age(request.getAge())
                        .passportNumber(request.getPassportNumber())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .post(request.getPost())
                        .rank(request.getRank())
                        .role(request.getRole())
                        .build();
                logisticCommanderService.save(logisticCommander);
                break;

            default:
                    log.info("Commander wasn't created.");
        }



        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }



    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }



}

