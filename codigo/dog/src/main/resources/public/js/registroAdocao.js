
var nomeAnimal;
var sexoAnimal;
var racaAnimal;
var especieAnimal;
var porteAnimal;
var idadeAnimal;
var pelagemAnimal;
var temperamentoAnimal;
var historicoAnimal;
var cepUser;
var img;
var user;


function atualiza (){
    nomeAnimal = document.getElementById('nomeAnimal').value;
    sexoAnimal = document.getElementById('sexo').value;
    racaAnimal = document.getElementById('raca').value;
    especieAnimal = document.getElementById('especie').value;
    porteAnimal = document.getElementById('porte').value;
    idadeAnimal = document.getElementById('idade').value;
    pelagemAnimal = document.getElementById('pelagem').value;
    temperamentoAnimal = document.getElementById('temperamento').value;
    cepUser = document.getElementById('cep').value;
    historicoAnimal = document.getElementById('historico').value;
    img = document.getElementById('imagens').value;
    user = JSON.parse(localStorage.getItem('user'));
}




function verificarOptionSelect(){

    atualiza();

    return (nomeAnimal.length > 0 && racaAnimal.length > 0 &&
            especieAnimal     > 0 && porteAnimal       > 0 && 
            idadeAnimal       > 0 && pelagemAnimal     > 0 &&
           temperamentoAnimal > 0 && cepUser.length           > 0 && 
            historicoAnimal.length > 0  && img.length > 0 && sexoAnimal.length > 0 &&
            user.id_user > 0);
}

async function submitResgistro(){
    if(verificarOptionSelect()){
        console.log("Verificacao dos inputs select passou sem problemas!");
    
        let data1 = new Date();
        let forme = document.getElementById('relatos');
        let form = new FormData(forme);

        form.append('nome', nomeAnimal);
        form.append('raca', racaAnimal);
        form.append('especie', especieAnimal);
        form.append('porte', porteAnimal);
        form.append('idade', idadeAnimal);
        form.append('pelagem', pelagemAnimal);
        form.append('temperamento', temperamentoAnimal);
        form.append('cep', cepUser);
        form.append('historico', historicoAnimal);
        form.append('sexo', sexoAnimal);
        form.append('user', user.id_user);
        form.append('data', data1.toISOString()); 

        const response = await fetch('http://52.167.125.62:4567/cadastroAdocao', { method: "POST", body: form});

        window.location.href = "http://52.167.125.62:4567/adocao.html"; 
    }
    else{
        alert("campos não preechidos");
    }
}

