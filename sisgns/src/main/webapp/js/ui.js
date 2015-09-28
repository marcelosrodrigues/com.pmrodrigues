$('[id*="cpf"]').mask("999.999.999-99");
$('[id*="cep"]').mask("99999-999");
$('[id*="cnpj"]').mask("99.999.999/9999-99");
$('[id*="numeroDocumento"]').mask("99.999.999/9999-99");
$('[id*="ddd"]').mask("999");
$('[id*="residencial.numero"]').mask("9999-9999");
$('[id*="celular.numero"]').mask("99999-9999");


$('[id="adicionar-telefone"]').click(function () {


    if ($('[id*="residencial.ddd"]').val() != "" && $('[id*="residencial.numero"]').val() != null) {

        $("ul#telefones").append("<li class=\"list-group-item\">(" + $('[id*="residencial.ddd"]').val() + ") "
            + $('[id*="residencial.numero"]').val()
            + "<input type=\"hidden\" name=\"object.residenciais[" + $("ul#telefones > li").length + "].id\" value=\"\" />"
            + "<input type=\"hidden\" name=\"object.residenciais[" + $("ul#telefones > li").length + "].ddd\" value=\"" + $('[id*="residencial.ddd"]').val() + "\" />"
            + "<input type=\"hidden\" name=\"object.residenciais[" + $("ul#telefones > li").length + "].numero\" value=\"" + $('[id*="residencial.numero"]').val() + "\" />"
            + "<button id=\"remover-telefone\" onclick='javascript:removerTelefone(this);' class=\"btn btn-danger btn-circle\" style='float: right; margin-top: -5px;' type=\"button\">"
            + "<i class=\"fa fa-minus\"></i>"
            + "</button>"
            + "</li>");

        $('[id*="residencial.ddd"]').val("");
        $('[id*="residencial.numero"]').val("");

    }
});

function removerTelefone(element) {
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

$('[id*="estado.nome"]').autocomplete({
    source: function (request, response) {
        $.ajax("/endereco/estado/" + $('[id*="estado.nome"]').val() + ".json")
            .done(function (data) {
                response(data.list);
            });
    },
    minLength: 2,
    select: function (event, ui) {
        $('[id*="bairro"]').val("");
        $('[id*="cidade"]').val("");
        $('[id*="logradouro"]').val("");
        $('[id*="bairro.nome"]').val("");
        $('[id*="cidade.nome"]').val("");
        $('[id*="estado"]').val(ui.item.id);
        $('[id*="estado.nome"]').val(ui.item.nome);
        return false;
    },
    focus: function (event, ui) {
        $('[id*="estado"]').val(ui.item.id);
        $('[id*="estado.nome"]').val(ui.item.nome);
        return false;
    }
}).data("ui-autocomplete")._renderItem = function (ul, item) {
    return $("<li>")
        .append("<a>" + item.nome + "</a>")
        .appendTo(ul);
};

$('[id*="cidade.nome"]').autocomplete({
    source: function (request, response) {
        $.ajax("/endereco/cidade/" + $('[id*="estado"]').val() + "/" + $('[id*="cidade.nome"]').val() + ".json")
            .done(function (data) {
                response(data.list);
            });
    },
    minLength: 3,
    select: function (event, ui) {
        $('[id*="cidade"]').val(ui.item.id);
        $('[id*="cidade.nome"]').val(ui.item.nome);
        return false;
    },
    focus: function (event, ui) {
        $('[id*="cidade"]').val(ui.item.id);
        $('[id*="cidade.nome"]').val(ui.item.nome);
        return false;
    }
}).data("ui-autocomplete")._renderItem = function (ul, item) {
    return $("<li>")
        .append("<a>" + item.nome + "</a>")
        .appendTo(ul);
};

$('[id*="bairro.nome"]').autocomplete({
    source: function (request, response) {
        $.ajax("/endereco/bairro/" + $('[id*="cidade"]').val() + "/" + $('[id*="bairro.nome"]').val() + ".json")
            .done(function (data) {
                response(data.list);
            });
    },
    minLength: 3,
    select: function (event, ui) {
        $('[id*="bairro"]').val(ui.item.id);
        $('[id*="bairro.nome"]').val(ui.item.nome);
        return false;
    },
    focus: function (event, ui) {
        $('[id*="bairro"]').val(ui.item.id);
        $('[id*="bairro.nome"]').val(ui.item.nome);
        return false;
    }
}).data("ui-autocomplete")._renderItem = function (ul, item) {
    return $("<li>")
        .append("<a>" + item.nome + "</a>")
        .appendTo(ul);
};



function doDelete(button) {

    var deleted = confirm($(button).attr("message"));

    if (deleted) {

        $("form").attr("action", $(button).attr("action"));
        $("form").attr("method", "POST");
        $("form").submit();

    }
}