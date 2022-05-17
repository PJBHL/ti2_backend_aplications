onload = () => {
  fetchJson();
};

function porte(porte) {
  let resp;
  switch (porte) {
    case 1:
      resp = "Pequeno";
      break;

    case 2:
      resp = "Médio";
      break;

    case 3:
      resp = "Grande";
      break;
  }

  return resp;
}

function pelagem(pelagem) {
  let resp;
  switch (pelagem) {
    case 1:
      resp = "Curta";
      break;

    case 2:
      resp = "Longa";
      break;

    case 3:
      resp = "Ondulado";
      break;
  }

  return resp;
}

function temperamento(temperamento) {
  let resp;
  switch (temperamento) {
    case 1:
      resp = "Calmo, se da bem com outros animais e é dócil";
      break;

    case 2:
      resp = "Bravo, inquieto e tem tendências a morder";
      break;

    case 3:
      resp = "Agressivo, cão mais agressivo e perigoso";
      break;
  }

  return resp;
}

async function fetchJson(
  url = "http://52.167.125.62:4567/buscaadocao",
  method = "get"
) {
  let tmp = localStorage.getItem("id_adocao");

  url += "/" + tmp;

  console.log(url);

  const response = await fetch(url, { method });
  const data = await response.json();
  construir(data.email, data.celular);

  console.log(data);

  var div = document.getElementById("JSarea");

  div.innerHTML = `

    <div class="col-12">
    <div class="row" id="header">
        <div class="col-lg-6 col-sm-12" id="ima">
            <img src="http://52.167.125.62:4567/${data.img_1}" class="img-thumbnail" id="imagem">
        </div>
        <div class="col-lg-6 col-sm-12" id="texto">
            <p class="mt-4"><strong>Nome:</strong> ${data.nome_animal}</p>
            <p><strong>Porte:</strong> ${porte(data.porte)}</p>
            <p><strong>Pelagem:</strong> ${pelagem(data.pelagem)}</p>
            <p><strong>Idade:</strong> ${data.idade}</p>
            <p><strong>Castrado em:</strong> ${data.data_post}</p>
            <p><strong>Temperamento:</strong> ${temperamento(
              data.temperamento
            )}</p>
            <p><strong>Histórico:</strong> ${data.historico} </p>
        </div>
    </div>
</div>
    `;
}

function construir(email, cel) {
  let card = document.getElementById("contato");

  card.innerHTML = `
        <div class="col-12">
                <p id="email"><b>Email:</b> ${email}</p>
                <p id="telefone"><b>Telefone:</b> ${cel}</p>
        </div>
        `;

  return card;
}
