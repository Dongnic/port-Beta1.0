package com.im.port.config.security.oauth;

import java.util.Optional;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.im.port.config.exception.OAuthProviderMissMatchException;
import com.im.port.config.security.auth.PrincipalDetails;
import com.im.port.config.security.oauth.factory.OAuth2UserInfoFactory;
import com.im.port.config.security.oauth.jwt.JwtTokenUtil;
import com.im.port.config.security.oauth.provider.OAuth2UserInfo;
import com.im.port.config.security.oauth.provider.ProviderType;
import com.im.port.repository.UserRepository;
import com.im.port.vo.dto.UserDto;
import com.im.port.vo.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
	private final JwtTokenUtil jwtTokenUtil;
	
    // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info(" ##### PrincipalOauth2UserService");
        log.info(" #### loadUser");
		
		OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회
        // code를 통해 구성한 정보
        log.info(" ### userRequest clientRegistration : " + userRequest.getClientRegistration());
        // token을 통해 응답받은 회원정보
        log.info(" ### oAuth2User : " + oAuth2User);
        log.info(" ## oAuth2User.getAttributes() : " + oAuth2User.getAttributes());
		try{
			return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException e){
            throw new OAuth2AuthenticationException(e.getMessage());
        } catch (Exception e){
			throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		log.info(" #### processOAuth2User");
        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
		ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());
		log.info(" ### providerType : " + providerType);
		log.info(" ### oAuth2UserInfo : " + oAuth2UserInfo);
		log.info(" ## oAuth2UserInfo Email : " + oAuth2UserInfo.getEmail());
		// null 값 들어오는 것도 수용하기 위해 Optional 사용 
		UserEntity user = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		log.info(" ### user : " + user);
        Optional<UserEntity> userOptional = Optional.ofNullable(user);
		log.info(" ### userOptional : " + userOptional);
		UserEntity userEntity;
        if (userOptional.isPresent()) {
			if (providerType != oAuth2UserInfo.getProvider()) {
				log.info(" ### provider type is not matches ");
				throw new OAuthProviderMissMatchException(
					"회원님은" + providerType + " 으로 로그인 하셨습니다. "
					+ oAuth2UserInfo.getProvider() + "로 로그인 해주세요"
					);
				}
			log.info(" ### User is already exist");
			userEntity = updateUser(oAuth2UserInfo, userOptional);
		} else{
			log.info(" ### Create New User");
			userEntity = createUser(oAuth2UserInfo, providerType);
		} 
		log.info(" ### jwtTokenUtil : " + jwtTokenUtil.generateToken(oAuth2UserInfo.getEmail()));
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }

	//넘어온 사용자 정보를 통해서 회원가입을 실행한다. 
	private UserEntity createUser(OAuth2UserInfo oAuth2UserInfo, ProviderType providerType) {
		log.info(" #### createUser");
		UserEntity userEntity = UserEntity.builder()
		.username(oAuth2UserInfo.getName())
		.email(oAuth2UserInfo.getEmail())
		.role("ROLE_MEMBER")
		.provider(providerType)
		.profileimage(oAuth2UserInfo.getProfileImage())
		.build();
		log.info(" ### userEntity" + userEntity.toDto());
		return userRepository.save(userEntity);
    }

	//사용자정보에 변경이 있다면 사용자 정보를 업데이트 해준다. 
	private UserEntity updateUser(OAuth2UserInfo oAuth2UserInfo, Optional<UserEntity> userOptional) {
		log.info(" #### updateUser");
		UserDto user = userOptional.get().toDto();
		if (oAuth2UserInfo.getName() != null && !user.getUsername().equals(oAuth2UserInfo.getName())) {
			user.setUsername(oAuth2UserInfo.getName());
		}
		if (oAuth2UserInfo.getEmail() != null && !user.getEmail().equals(oAuth2UserInfo.getEmail())){
			user.setEmail(oAuth2UserInfo.getEmail());
		}
		if (oAuth2UserInfo.getProfileImage() != null && !user.getProfileimage().equals(oAuth2UserInfo.getProfileImage())) {
			user.setProfileimage(oAuth2UserInfo.getProfileImage());
		}
		return userRepository.save(user.toEntity());
	}
}