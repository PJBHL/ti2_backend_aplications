onload= () => {
    fetchJson();
}

async function fetchJson(url = 'http://52.167.125.62:4567/denuncias', method = 'get')
{
    const response = await fetch(url, { method });
    const data = await response.json();

    var div = document.getElementById("JSarea");
    for(let i = 0; i < data.length ; i++){
        aux = new cards (data[i].tipo_denuncia, data[i].relato);
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

class cards {
    constructor(titulo, texto){
        this.texto = texto;
        this.tipo = traduz(titulo);

    }

    construir () {       
        let card = document.createElement("JSarea");

        
        card.innerHTML = `

        <div class="card card-denuncia">
            
                <img src="imgs/home/logo.png" class="card-img-top" alt="">
                <div class="card-body">
                    <div class="card-text">
                        <h5 class="card-title">${this.tipo}</h5>
                        <p >${this.texto}</p>
                    </div>     
                </div>   
            
        </div>
                   
        `;
       return card;      
    }
}