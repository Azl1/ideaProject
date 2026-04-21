function sum(a){
    return a + 4;
}

function fact(n){
    let res = 1;
    for (let i = 1; i <= n; i++) {
        res *= i;
    }
    return res;
}

$(document).ready(function () {
    $('#button_save').click(function () {
        let a = parseInt($('#input_number').val());
        let fio = $('#input_fio').val();

        let result = sum(a);
        let resultFact = fact(a);
        $('#res').val(fio + ", " + result + ", " + resultFact);
        $('#simple_text').html(`Another text sum: ${result}<br>fact: ${resultFact}.`);
    });
});

