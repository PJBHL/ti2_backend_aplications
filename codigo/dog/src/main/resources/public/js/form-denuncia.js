var tipo, data, rua, bairro, cidade, UF, informacao, user, validacao;

onload = () => {
    user = JSON.parse(localStorage.getItem('user'));
    tipo1 = document.getElementById("tipo");
    data1 = document.getElementById("data");
    rua1 = document.getElementById("info1");
    bairro1 = document.getElementById("info3");
    cidade1 = document.getElementById("info2");
    uf1 = document.getElementById("info4");
    informacao1 = document.getElementById("txt");
    validacao = false;
}

function dataForm () {
    let dia, mes, ano;
    dia = data1.value[0] + data1.value[1] + data1.value[2] + data1.value[3];
    mes = data1.value[5] + data1.value[6];
    ano = data1.value[8] + data1.value[9] ;

    return new Date(ano, dia, mes);    
}

function valida (cop){
    if(data1.value != null &&
       uf1.value != "" &&
       rua1.value != "" &&
       bairro1.value != "" &&
       cidade1.value != "" &&
       informacao1.value != "")
        return true;

    return false;
}

async function caddenuncia(){

    if(user.id_user > 0 && valida()){
        let teste = false;
        let url = 'http://viacep.com.br/ws/';
        var data2;
        url += uf1.value + "/" + cidade1.value + "/" + rua1.value + "/json";

        try{
            console.log(url);
            const response = await fetch(url ,{ method: "GET"});
            data2 = await response.json();
            teste = true;
        } 
        catch{
            alert("endereço errado");
            teste = false;
        }

        let cep2 = data2[0].cep[0]  + data2[0].cep[1]  + data2[0].cep[2]  + data2[0].cep[3]  + data2[0].cep[4]  + 
        data2[0].cep[6]  + data2[0].cep[7]  + data2[0].cep[8];

        let cop = {
            data_post: dataForm().toISOString(),
            data_denuncia: new Date().toISOString(),
            tipo: tipo1.value,
            cep: cep2,
            relato: informacao1.value,
            user_denuncia: user.id_user
        };

        if(teste){
            const response = await fetch('http://52.167.125.62:4567/cadastroDenuncia', { method: "POST", body: JSON.stringify(cop)});
            const data = await response.json();

            window.location.href = "http://52.167.125.62:4567/denuncia.html";   
        }    
    }
}