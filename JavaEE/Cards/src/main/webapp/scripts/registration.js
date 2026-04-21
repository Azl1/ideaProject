$('#btn-go-login').click(function () {
        $(location).attr('href',
            "http://localhost:8084/Card/login.html");
    }
);

$('#btn-sign-up').click(function () {
        let login = $('#email').val();
        let password = $('#password').val();
        let name = $('#name').val();
        $.ajax({
            type: "POST",
            url: `user`,
            data: JSON.stringify({"login": login, "password": password, "name": name}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: [function (data) {
                $('#email').val('');
                $('#password').val('');
                $('#name').val('');
                $('.popup-fade').fadeIn();
            }],
            error: [function () {
                alert("Пользователь с таким логином уже зарегистрирован!!!")
            }]
        })
    }
);

$('#btn-ok').click(function () {
    $('.popup-fade').fadeOut();
    $(location).attr('href',
        "http://localhost:8084/Card/login.html");
});