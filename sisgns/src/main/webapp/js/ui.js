$('[id*="cpf"]').mask("999.999.999-99");
$('[id*="cep"]').mask("99999-999");
$('[id*="cnpj"]').mask("99.999.999/9999-99");
$('[id*="numeroDocumento"]').mask("99.999.999/9999-99");


$('[id*="cep"]').blur(function () {


    $.ajax("/endereco/" + $(this).val().replace('-', '') + "/logradouro.json")
        .done(function (endereco) {
            $('[id*="logradouro"]').val(endereco.logradouro.logradouro);
            $('[id*="bairro"]').val(endereco.logradouro.bairro.nome);
            $('[id*="cidade"]').val(endereco.logradouro.bairro.cidade.nome);
            $('[id*="estado"]').val(endereco.logradouro.bairro.cidade.estado.nome);
        });
})

function doDelete(button) {

    var deleted = confirm($(button).attr("message"));

    if (deleted) {

        $("form").attr("action", $(button).attr("action"));
        $("form").attr("method", "POST");
        $("form").submit();

    }
}