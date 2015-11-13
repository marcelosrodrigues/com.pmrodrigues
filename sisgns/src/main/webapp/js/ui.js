$('[id*="cpf"]').mask("999.999.999-99");
$('[id*="cep"]').mask("99999-999");
$('[id*="cnpj"]').mask("99.999.999/9999-99");
$('[id*="numeroDocumento"]').mask("99.999.999/9999-99");
$('[id*="ddd"]').mask("999");
$('[id*="residencial.numero"]').mask("9999-9999");
$('[id*="celular.numero"]').mask("99999-9999");

function permitApenasDinheiro(e) {
    if (e.which > 47 && e.which < 58) {
        return true;
    } else if (String.fromCharCode(e.which) == "," && $(this).val().indexOf(",") < 0) {
        return true;
    } else {
        return false;
    }
}

$('[id*=precos]').on('keypress', permitApenasDinheiro);

$('[id="adicionar-regra"]').click(function () {

    if ($('[id*="comissionamento.nome"]').val() != null && $('[id*="comissionamento.percentual"]').val() != null && $('[id*="comissionamento.ordem"]').val() != null) {
        $("#regras-de-comissionamento > table > tbody").append("<tr>"
            + "<td>" + $('[id*="comissionamento.nome"]').val()
            + "<input type=\"hidden\" name=\"object.regra[" + $("#regras-de-comissionamento > table > tbody > tr ").length + "].id\" value=\"\" />"
            + "<input type=\"hidden\" name=\"object.regra[" + $("#regras-de-comissionamento > table > tbody > tr ").length + "].nome\" value=\"" + $('[id*="comissionamento.nome"]').val() + "\" />"
            + "<input type=\"hidden\" name=\"object.regra[" + $("#regras-de-comissionamento > table > tbody > tr ").length + "].percentual\" value=\"" + $('[id*="comissionamento.percentual"]').val() + "\" />"
            + "<input type=\"hidden\" name=\"object.regra[" + $("#regras-de-comissionamento > table > tbody > tr ").length + "].ordem\" value=\"" + $('[id*="comissionamento.ordem"]').val() + "\" />"
            + "</td>"
            + "<td align=\"center\" width=\"5%\">" + $('[id*="comissionamento.percentual"]').val() + "</td>"
            + "<td align=\"center\" width=\"5%\">" + $('[id*="comissionamento.ordem"]').val() + "</td>"
            + "<td align=\"center\" width=\"5%\">"
            + "<button onclick=\"javascript:removerTR(this)\" type=\"button\" class=\"btn btn-danger btn-circle\">"
            + "<i class=\"fa fa-times\"></i></button></td></tr>");

        $('[id*="comissionamento.nome"]').val("");
        $('[id*="comissionamento.percentual"]').val("")
        $('[id*="comissionamento.ordem"]').val("")
    }

});

$('[id="adicionar-telefone"]').click(function () {


    if ($('[id*="residencial.ddd"]').val() != "" && $('[id*="residencial.numero"]').val() != null) {

        var telefone = "(" + $('[id*="residencial.ddd"]').val() + ") " + $('[id*="residencial.numero"]').val();

        $("ul#telefones").append("<li class=\"list-group-item\">" + telefone
            + "<input type=\"hidden\" name=\"object.residenciais[" + $("ul#telefones > li").length + "].id\" value=\"\" />"
            + "<input type=\"hidden\" name=\"object.residenciais[" + $("ul#telefones > li").length + "].ddd\" value=\"" + $('[id*="residencial.ddd"]').val() + "\" />"
            + "<input type=\"hidden\" name=\"object.residenciais[" + $("ul#telefones > li").length + "].numero\" value=\"" + $('[id*="residencial.numero"]').val() + "\" />"
            + "<button id=\"remover-telefone\" telefone=\"" + telefone + "\" onclick='javascript:removerLI(this);' class=\"btn btn-danger btn-circle\" style='float: right; margin-top: -5px;' type=\"button\">"
            + "<i class=\"fa fa-minus\"></i>"
            + "</button>"
            + "</li>");

        $('[id*="residencial.ddd"]').val("");
        $('[id*="residencial.numero"]').val("");

    }
});

function removerTR(element) {
    $(element).parent()
        .parent()
        .remove();
}

function removerLI(element) {
    $(element).parent().remove();
}

$('[id*="cep"]').blur(function () {

    var cep = $(this).val().replace('-', '');
    if (cep != '') {
        $.ajax("/endereco/logradouro/" + $(this).val().replace('-', '') + ".json")
            .done(function (endereco) {
                $('[id*="bairro"]').val(endereco.logradouro.bairro.id);
                $('[id*="cidade"]').val(endereco.logradouro.bairro.cidade.id);
                $('[id*="estado"]').val(endereco.logradouro.bairro.cidade.estado.id);
                $('[id*="logradouro"]').val(endereco.logradouro.logradouro);
                $('[id*="bairro.nome"]').val(endereco.logradouro.bairro.nome);
                $('[id*="cidade.nome"]').val(endereco.logradouro.bairro.cidade.nome);
                $('[id*="estado.nome"]').val(endereco.logradouro.bairro.cidade.estado.nome);
            });
    }
})

function doDelete(button) {

    var deleted = confirm($(button).attr("message"));

    if (deleted) {

        $("form").attr("action", $(button).attr("action"));
        $("form").attr("method", "POST");
        $("form").submit();

    }
}
