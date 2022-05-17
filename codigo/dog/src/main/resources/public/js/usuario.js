var user;

onload = () => {
    user = JSON.parse(localStorage.getItem('user'));
    fetchJson();
    fetchJson2();
    fetchJson3();
}

async function fetchJson(url = 'http://52.167.125.62:4567/minhasadocao', method = 'get')
{
    url += "/" + user.id_user;
    const response = await fetch(url, { method });
    const data = await response.json();
    console.log(data);
    var div = document.getElementById("doados");
    for(let i = 0; i < data.length ; i++){
        aux = new cardDoacao (data[i].id_doacao, data[i].img_1, data[i].idade, data[i].nome_animal, data[i].raca);  
        div.appendChild(aux.construir());
    }    
}

class cardDoacao {
  constructor(id_adocao, img, idade, nomeAnimal, especie) {
    this.id_adocao = id_adocao;
    this.img = img;
    this.idade = idade;
    this.nomeAnimal = nomeAnimal;
    this.especie = especie;
  }

  construir() {
    let card = document.createElement("div");

    card.innerHTML = `

        <div class="card card-adocao">
                <img src="http://52.167.125.62:4567/${this.img} " class="card-img-top border-bottom">
                <div class="card-body">
                    <div class="card-text">
                        <p><b>Nome:</b> ${this.nomeAnimal}</p>
                        <p><b>Raça:</b> ${this.especie}</p>
                        <p><b>Idade:</b> ${this.idade}</p>
                    </div>
                    <a href="http://52.167.125.62:4567/user.html" class="btn btn-warning adocao adotar">Apagar</a>
                    </button>
                </div>
            </div>
                   
        `;

    card.querySelector(".adotar").onclick = () => {
      console.log(this.id_adocao);
      excluirAdocao(this.id_adocao);
    };
    return card;
  }
}

async function fetchJson2(url = 'http://52.167.125.62:4567/minhasdenuncias', method = 'get')
{
    url += "/" + user.id_user;
    const response = await fetch(url, { method });
    const data = await response.json();
    console.log(data);
    var div = document.getElementById("denuncias");
    for(let i = 0; i < data.length ; i++){
        aux = new cardDenuncia (data[i].tipo_denuncia, data[i].relato, data[i].id_denuncia);
        div.appendChild(aux.construir());
    }
}

function traduz (tipy) {
    if(tipy == 1){
        return "Abuso de animais";
    }else{
        if(tipy == "2"){
            return "Abandono de animais";
        }else{
            if(tipy == "3"){
                return "defull1";
            }else{
                return "defull2";
            }
        }
    }
}

class cardDenuncia {
    constructor(titulo, texto, id_denuncia){
        this.texto = texto;
        this.tipo = traduz(titulo);
        this.id_denuncia = id_denuncia;
    }

    construir () {       
            let card = document.createElement("div");
        card.innerHTML = `
        <div class="card card-denuncia">
            
                <img src="http://52.167.125.62:4567/padrao.png" class="card-img-top" alt="">
                <div class="card-body">
                    <div class="card-text">
                        <h5 class="card-title">${this.tipo}</h5>
                        <p >${this.texto}</p>
                    </div>  
                    <a href="http://52.167.125.62:4567/user.html" class="btn btn-warning denuncia denunciar">Apagar</a>
                    </button>   
                </div>   
            
        </div>
                   
        `;
    card.querySelector(".denuncia").onclick = () => {
      console.log(this.id_denuncia);
      excluirDenuncia(this.id_denuncia);
    
    };
       return card;      
    }
}

async function fetchJson3(url = 'http://52.167.125.62:4567/minhasdicas', method = 'GET') 
{
    url += "/" + user.id_user;
    const response = await fetch(url, { method });
    const data = await response.json();
    console.log(data);
    var div = document.getElementById("dicas");

    console.log(data);

    for (let i = 0; i < data.length; i++) {

        let aux = new cardDicas (data[i].id_dica, data[i].titulo, data[i].descricao, data[i].user_dica, data[i].img_dica);

        div.appendChild(aux.construir());
    }
}

class cardDicas {

    constructor(id_dica, titulo, descricao, user_dica, img) {

        this.id_dica = id_dica;
        this.titulo = titulo;
        this.descricao = descricao;
        this.user_dica = user_dica;
        this.img = img;
    }

    construir() {

            let card = document.createElement("div");

        card.innerHTML = `
        <div class="card card-dica">
            <img src="http://52.167.125.62:4567/${this.img}" class="card-img-top" alt="">
            <div class="card-body">
                <div class="card-text">
                    <h5 class="card-title"> ${this.titulo}</h5>
                    <p> ${this.descricao}</p>
                </div>
                    <a href="http://52.167.125.62:4567/user.html" class="btn btn-warning dica dicar">Apagar</a>
                    </button>   
            </div>   
        </div>
        `;
         card.querySelector(".dica").onclick = () => {
                    console.log(this.id_dica);
                    excluirDica(this.id_dica);
          };
        return card;
    }
}



async function excluirDica(id) {

    console.log("exclui dica ===" + id);

    let foo = "http://52.167.125.62:4567/execluirDicas/" + id;
    
    const response = await fetch(foo, { method:"GET"});
    //const data = await response.json();

}

async function excluirDenuncia(id) {
    let foo = "http://52.167.125.62:4567/excluiDenuncias/" + id;
    
    const response = await fetch(foo, { method:"GET"});
    const data = await response.json();
}

async function excluirAdocao(id) {
    let foo = "http://52.167.125.62:4567/excluiAdocao/" + id;
    
    const response = await fetch(foo, { method:"GET"});
    const data = await response.json();
}












