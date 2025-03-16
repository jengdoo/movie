package com.example.movies.service.implement;

import com.example.movies.dto.request.IntrospectRequest;
import com.example.movies.dto.request.UserLoginRequest;
import com.example.movies.dto.request.UserRegisterRequest;
import com.example.movies.dto.response.IntrospectResponse;
import com.example.movies.dto.response.UserLoginResponse;
import com.example.movies.dto.response.UserResponse;
import com.example.movies.entity.*;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.UserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signer-key}")
    protected String SIGNER_KEY;
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::convertUserToUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id).map(UserResponse::convertUserToUserResponse).orElseThrow(()->new RuntimeException("not found user with id " + id));
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsByUsername(userRegisterRequest.getUsername())) {
            throw new RuntimeException("username already exists");
        }
        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new RuntimeException("email already exists");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(9);
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setRole(Role.USER);
        user.setIsVerified(true);
        user.setStatus(Status.ACTIVE);
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setVipLevel(VipLevel.BRONZE);
        userRepository.save(user);
        return UserResponse.convertUserToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRegisterRequest userRegisterRequest) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("not found user with id " + id));
        user.setUsername(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPasswordHash(userRegisterRequest.getPassword());
        user.setIsVerified(true);
        user.setStatus(Status.ACTIVE);
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setVipLevel(VipLevel.BRONZE);
        userRepository.save(user);
        return UserResponse.convertUserToUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("not found user with id " + id));
       userRepository.delete(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUsernameOrEmail(userLoginRequest.getUsernameOrEmail(),userLoginRequest.getUsernameOrEmail()).orElseThrow(()->new RuntimeException("wrong username or email"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(9);
        boolean status = passwordEncoder.matches(userLoginRequest.getPassword(), user.getPasswordHash());
        if(!status) {
            throw new RuntimeException("wrong account or password");
        }
        String token = generateToken(userLoginRequest.getUsernameOrEmail());
        return UserLoginResponse.builder()
                .token(token)
                .loginStatus(true)
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        String token = introspectRequest.getToken();
        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);
           boolean verified = signedJWT.verify(verifier);
           Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
           return IntrospectResponse.builder()
                   .valid(verified && expiration.after(new Date()))
                   .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("jengdoo.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim","Custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
