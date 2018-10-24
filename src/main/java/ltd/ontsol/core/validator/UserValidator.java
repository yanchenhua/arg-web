package ltd.ontsol.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ltd.ontsol.core.dto.UserDTO;
import ltd.ontsol.core.service.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
        if (user.getUserName().length() < 3 || user.getUserName().length() > 32) {
            errors.rejectValue("userName", "Size.userForm.username");
        }
        if (service.findByUserName(user.getUserName()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

    }
}
