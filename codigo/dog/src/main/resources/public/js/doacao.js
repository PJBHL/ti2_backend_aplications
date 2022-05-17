onload = () => {
    fetchJson();
}

async function fetchJson(url = 'http://52.167.125.62:4567/animaisdoacao', method = 'get')
{
    const response = await fetch(url, { method });
    const data = await response.json();

    var div = document.getElementById("JSarea");
    for(let i = 0; i < data.length ; i++){
        aux = new cards (data[i].id_doacao, data[i].img_1, data[i].idade, data[i].nome_animal, data[i].raca);  
        div.appendChild(aux.construir());
    }    
}

class cards {
    constructor(id_adocao, img, idade, nomeAnimal, especie){

        this.id_adocao = id_adocao;
        this.img = img;
        this.idade = idade;
        this.nomeAnimal = nomeAnimal;
        this.especie = especie;
    }

    construir () {       
        let card = document.createElement("JSarea");

        card.innerHTML = `

        <div class="card card-adocao">
                <img src="http://52.167.125.62:4567/${this.img} " class="card-img-top border-bottom">
                <div class="card-body">
                    <div class="card-text">
                        <p><b>Nome:</b> ${this.nomeAnimal}</p>
                        <p><b>Raça:</b> ${this.especie}</p>
                        <p><b>Idade:</b> ${this.idade}</p>
                    </div>
                    <a href="infos1.html" class="btn btn-warning adocao adotar">Adotar</a>
                    </button>
                </div>
            </div>
                   
        `;

        card.querySelector('.adotar').onclick = () => {

        console.log(this.id_adocao);
        localStorage.setItem("id_adocao", this.id_adocao );
       }
       return card;      
    }
}