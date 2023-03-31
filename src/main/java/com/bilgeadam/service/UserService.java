package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserRegisterRequestDto;
import com.bilgeadam.dto.response.UserLoginResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.utility.ICrudService;
import com.bilgeadam.utility.enums.ECustonEnum;
import com.bilgeadam.utility.enums.EStatus;
import com.bilgeadam.utility.enums.EUserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements ICrudService<User, Integer> {
    private final IUserRepository userRepository;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> t) {
        return null;
    }

    /**
     * !!dto ile yapılacak
     */
    @Override
    public User update(User user) {
        return null;
    }

    //Sadece admin rolüne sahip kişiler bu işlemi gerçekleştirebilir.
    @Override
    public User delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            optionalUser.get().setStatus(EStatus.INACTIVE);
            userRepository.save(optionalUser.get());
            return optionalUser.get();
        }else {
            throw new NullPointerException("Böyle bir kullanıcı yok.");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()){
            throw new NullPointerException("Liste boş");
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser;
        }else {
            throw new NullPointerException("BÖyle bir kullanıcı yok");
        }
    }

    //basic builder register
    public User register(String name, String surname, String email, String password, String repassword){
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .repassword(repassword)
                .build();
        if (!password.equals(repassword) || password.isBlank() || repassword.isBlank()){
            throw new RuntimeException("Şifreler aynı değildir.");
        }else {
            return userRepository.save(user);
        }
    }

    //basic login
    public String login(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("Böyle bir kullanıcı bulunamadı");
        }
        return "Giriş başarılı";
    }

    //dto-register
    public UserRegisterRequestDto registerDto(UserRegisterRequestDto dto){
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .repassword(dto.getRepassword())
                .build();
        if (!dto.getPassword().equals(dto.getRepassword())
                || dto.getPassword().isBlank() || dto.getRepassword().isBlank()){
            throw new RuntimeException("Şifreler aynı değildir.");
        }else {
            userRepository.save(user);
        }
        return dto;
    }
    
    //mapper-register
    //Aynı email ile ikinci defa kayıt işlemi yapılmamalıdır. Eğer kayıt olan kişi superadmin@mail.com ise
    //UserType=ADMIN ve Status=ACTIVE olmalıdır.
    public UserRegisterRequestDto registerMapper(UserRegisterRequestDto dto) {
        User user = IUserMapper.INSTANCE.toUserRegisterDto(dto);
        if (userRepository.findByEmailEqualsIgnoreCase(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        } else if (!dto.getPassword().equals(dto.getRepassword())
                || dto.getPassword().isBlank() || dto.getRepassword().isBlank()) {
            throw new RuntimeException("Şifre alanlarını boş bırakmayınız.");
        } else if (dto.getEmail().equals("superadmin@mail.com")) {
            user.setUserType(EUserType.ADMIN);
            user.setStatus(EStatus.ACTIVE);
        }
        userRepository.save(user);
        return dto;
    }
    //dto-login
    public String loginDto(UserLoginResponseDto dto){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()){
            throw new NotFoundException("Email veya şifre bilgisi hatalı");
        }else {
            return "Giriş başarılı \n" + dto.getEmail();
        }
    }

    //mapper-login
    public UserLoginResponseDto loginMapper(UserLoginResponseDto dto){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()){
            throw new NotFoundException("Email ve şifre bilgisi hatalı");
        }else {
            return IUserMapper.INSTANCE.toUserLoginDto(optionalUser.get());
        }
    }
    //custon login -->Bertan
    public ResponseEntity costomLogin(UserLoginResponseDto dto){
        Map<ECustonEnum,Object> hm = new HashMap<>();
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()) {
            hm.put(ECustonEnum.status,false);
            hm.put(ECustonEnum.message,"email yada şifre hatalıdır");
            return new ResponseEntity(hm, HttpStatus.UNAUTHORIZED);
        } else{
            hm.put(ECustonEnum.status, true);
            hm.put(ECustonEnum.result, dto);
            hm.put(ECustonEnum.message, "giriş başarılı");
            return new ResponseEntity(hm, HttpStatus.OK);
        }

    }

    //kullanıcıları ismine göre sırala
    public List<User> findByOrderByName(){
        return userRepository.findAllByOrderByName();
    }

    public List<User> findAllByNameContainsIgnoreCase(String value){
        List<User> users = userRepository.findAllByNameContainsIgnoreCase(value);
        if (users.isEmpty()){
            throw new NotFoundException("Liste boş");
        }
        return users;
    }

    public Boolean existsByNameIgnoreCase(String value){
        return userRepository.existsByNameIgnoreCase(value);
    }

    public List<User> findByEmailIgnoreCase(String email){
        List<User> users = userRepository.findByEmailIgnoreCase(email);
        if (users.isEmpty()){
            throw new NotFoundException("Liste boş");
        }
        return users;
    }

    public List<User> findPasswordByLength(int length){
       return userRepository.findPasswordByLength2(length);
    }
    public List<User> findPasswordByLengthNative(int length){
        return userRepository.findPasswordByLength3(length);
    }
}
