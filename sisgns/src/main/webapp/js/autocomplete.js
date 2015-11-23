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
            $('[id="object.endereco.estado"]').val(ui.item.id);
            $('[id="object.endereco.cidade.estado"]').val(ui.item.id);
            $('[id="object.endereco.bairro.cidade.estado"]').val(ui.item.id);
            $('[id="estado.nome"]').val(ui.item.nome);
            return false;
        },
        focus: function (event, ui) {
            $('[id="object.endereco.estado"]').val(ui.item.id);
            $('[id="object.endereco.cidade.estado"]').val(ui.item.id);
            $('[id="object.endereco.bairro.cidade.estado"]').val(ui.item.id);
            $('[id*="estado.nome"]').val(ui.item.nome);
            return false;
        },
        response: function (event, ui) {
            if (ui.content.length == 1) {
                $('[id="object.endereco.estado"]').val(ui.content[0].id);
                $('[id="object.endereco.cidade.estado"]').val(ui.content[0].id);
                $('[id="object.endereco.bairro.cidade.estado"]').val(ui.content[0].id);
                $('[id*="estado.nome"]').val(ui.content[0].nome);
                $(this).autocomplete("close");
            }
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
            $('[id="object.endereco.cidade"]').val(ui.item.id);
            $('[id="object.endereco.bairro.cidade"]').val(ui.item.id);
            $('[id*="cidade.nome"]').val(ui.item.nome);
            return false;
        },
        focus: function (event, ui) {
            $('[id="object.endereco.cidade"]').val(ui.item.id);
            $('[id="object.endereco.bairro.cidade"]').val(ui.item.id);
            $('[id*="cidade.nome"]').val(ui.item.nome);
            return false;
        },
        response: function (event, ui) {
            if (ui.content.length == 1) {
                $('[id="object.endereco.cidade"]').val(ui.content[0].id);
                $('[id="object.endereco.bairro.cidade"]').val(ui.content[0].id);
                $('[id*="cidade.nome"]').val(ui.content[0].nome);
                $(this).autocomplete("close");
            }
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
        },
        response: function (event, ui) {
            if (ui.content.length == 1) {
                $('[id*="bairro"]').val(ui.content[0].id);
                $('[id*="bairro.nome"]').val(ui.content[0].nome);
                $(this).autocomplete("close");
            }
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
        },
        response: function (event, ui) {
            if (ui.content.length == 1) {
                $('[id*="administradora"]').val(ui.content[0].id);
                $('[id*="administradora.nome"]').val(ui.content[0].nome);
                $(this).autocomplete("close");
            }
        }
    }).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="modalidade.nome"]').each(function (i, item) {
    $(item).autocomplete({
        source: function (request, response) {
            $.ajax("/modalidade/" + $(item).val() + ".json")
                .done(function (data) {
                    response(data.list);
                });
        },
        minLength: 2,
        select: function (event, ui) {
            $('[id*="modalidade"]').val(ui.item.id);
            $('[id*="modalidade.nome"]').val(ui.item.nome);
            return false;
        },
        focus: function (event, ui) {
            $('[id*="administradora"]').val(ui.item.id);
            $('[id*="administradora.nome"]').val(ui.item.nome);
            return false;
        },
        response: function (event, ui) {
            if (ui.content.length == 1) {
                $('[id*="modalidade"]').val(ui.content[0].id);
                $('[id*="modalidade.nome"]').val(ui.content[0].nome);
                $(this).autocomplete("close");
            }
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
            response: function (event, ui) {
                if (ui.content.length == 1) {
                    $('[id*="operadora"]').val(ui.content[0].id);
                    $('[id*="operadora.nome"]').val(ui.content[0].nome);
                    $(this).autocomplete("close");
                }
            }
        }
    ).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="plano.nome"]').each(function (i, item) {
    $(item).autocomplete({
            source: function (request, response) {
                $.ajax("/plano/" + $('[id*="operadora"]').val() + "/" + $(item).val() + ".json")
                    .done(function (data) {
                        response(data.list);
                    });
            },
            minLength: 2,
            select: function (event, ui) {
                $('[id*="plano"]').val(ui.item.id);
                $('[id*="plano.nome"]').val(ui.item.nome);
                return false;
            },
            focus: function (event, ui) {
                $('[id*="plano"]').val(ui.item.id);
                $('[id*="plano.nome"]').val(ui.item.nome);
                return false;
            },
            response: function (event, ui) {
                if (ui.content.length == 1) {
                    $('[id*="plano"]').val(ui.content[0].id);
                    $('[id*="plano.nome"]').val(ui.content[0].nome);
                    $(this).autocomplete("close");
                }
            }
        }
    ).data("ui-autocomplete")._renderItem = renderAutocomple;
});

$('[id*="vendidoPor.nome"]').each(function (i, item) {
    $(item).autocomplete({
            source: function (request, response) {
                $.ajax("/corretor/" + $(item).val() + ".json")
                    .done(function (data) {
                        response(data.list);
                    });
            },
            minLength: 2,
            select: function (event, ui) {
                $('[id*="vendidoPor"]').val(ui.item.id);
                $('[id*="vendidoPor.nome"]').val(ui.item.nome);
                return false;
            },
            focus: function (event, ui) {
                $('[id*="vendidoPor"]').val(ui.item.id);
                $('[id*="vendidoPor.nome"]').val(ui.item.nome);
                return false;
            },
            response: function (event, ui) {
                if (ui.content.length == 1) {
                    $('[id*="vendidoPor"]').val(ui.content[0].id);
                    $('[id*="vendidoPor.nome"]').val(ui.content[0].nome);
                    $(this).autocomplete("close");
                }
            }
        }
    ).data("ui-autocomplete")._renderItem = renderAutocomple;
});

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