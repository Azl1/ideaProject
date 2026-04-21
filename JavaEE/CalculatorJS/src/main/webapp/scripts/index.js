function sumPlus(a, b){
    return a + b;
}

function sumMinus(a, b){
    return a - b;
}

function sumMultiply(a, b){
    return a * b;
}

function sumDivide(a, b){
    return a / b;
}


$(document).ready(function (){
    $('#plusValue').click(function () {
        let a = parseInt($('#number1').val());
        let b = parseInt($('#number2').val());

        let results = sumPlus(a, b);
        $('#result').val(`${a} + ${b} =   ${results}`);
        $('#calculate').html(`Another text sum: ${results}<br>`);
    });

    $('#minusValue').click(function () {
        let a = parseInt($('#number1').val());
        let b = parseInt($('#number2').val());

        let results = sumMinus(a, b);
        $('#result').val(`${a} - ${b} =   ${results}`);
        $('#calculate').html(`Another text sum: ${results}<br>`);
    });

    $('#multiplyValue').click(function () {
        let a = parseInt($('#number1').val());
        let b = parseInt($('#number2').val());

        let results = sumMultiply(a, b);
        $('#result').val(`${a} * ${b} =   ${results}`);
        $('#calculate').html(`Another text sum: ${results}<br>`);
    });

    $('#divideValue').click(function () {
        let a = parseInt($('#number1').val());
        let b = parseInt($('#number2').val());

        let results = sumDivide(a, b);
        $('#result').val(`${a} / ${b} =   ${results}`);
        $('#calculate').html(`Another text sum: ${results}<br>`);
    });
});



