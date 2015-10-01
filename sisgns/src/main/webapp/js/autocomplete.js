function renderAutocomple(ul, item) {
    return $("<li>")
        .append("<a>" + item.nome + "</a>")
        .appendTo(ul);
}

$('[id*="estado.nome"]').each(function (i, item) {
    $(item).autocomplete({
        source: function (request, response) {
            $.ajax("/endereco/estado/" + $(item).val() + ".json")
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
    }).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="cidade.nome"]').each(function (i, item) {
    $(item).autocomplete({
        source: function (request, response) {
            $.ajax("/endereco/cidade/" + $('[id*="estado"]').val() + "/" + $(item).val() + ".json")
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
    }).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="bairro.nome"]').each(function (i, item) {
    $(item).autocomplete({
        source: function (request, response) {
            $.ajax("/endereco/bairro/" + $('[id*="cidade"]').val() + "/" + $(item).val() + ".json")
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
    }).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="administradora.nome"]').each(function (i, item) {
    $(item).autocomplete({
        source: function (request, response) {
            $.ajax("/administradora/" + $(item).val() + ".json")
                .done(function (data) {
                    response(data.list);
                });
        },
        minLength: 2,
        select: function (event, ui) {
            $('[id*="administradora"]').val(ui.item.id);
            $('[id*="administradora.nome"]').val(ui.item.nome);
            return false;
        },
        focus: function (event, ui) {
            $('[id*="administradora"]').val(ui.item.id);
            $('[id*="administradora.nome"]').val(ui.item.nome);
            return false;
        }
    }).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="operadora.nome"]').each(function (i, item) {
    $(item).autocomplete({
            source: function (request, response) {
                $.ajax("/operadora/" + $(item).val() + ".json")
                    .done(function (data) {
                        response(data.list);
                    });
            },
            minLength: 2,
            select: function (event, ui) {
                $('[id*="operadora"]').val(ui.item.id);
                $('[id*="operadora.nome"]').val(ui.item.nome);
                return false;
            },
            focus: function (event, ui) {
                $('[id*="operadora"]').val(ui.item.id);
                $('[id*="operadora.nome"]').val(ui.item.nome);
                return false;
            },
        }
    ).data("ui-autocomplete")._renderItem = renderAutocomple;
})

