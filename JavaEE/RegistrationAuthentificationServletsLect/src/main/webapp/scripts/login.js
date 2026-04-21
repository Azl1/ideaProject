$('#btn-go-to-sign-up').click(function () {
        $(location).attr('href', "http://localhost:8082/RegistrationAuthentificationServletsLect/registration.html");
    }
);

$('#btn-login').click(function () {
        let login = $('#email').val();
        let password = $('#password').val();
        $.ajax({
            url: `user?login=${login}&password=${password}`,
            method: "GET",
            success: [function (result) {
                $(location).attr('href', "http://localhost:8082/RegistrationAuthentificationServletsLect/index.html");
            }],
            error: [function (e) {
                alert("Неверный логин или пароль!!!")
            }]
        });
    }
);