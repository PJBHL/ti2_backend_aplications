

onload = () => {

    let teste = localStorage.getItem('user');

    console.log(teste);

    teste = JSON.parse(teste);
    console.log(teste.id_user);
}

async function cadastroDica () {

    let titulo    = document.getElementById('tituloDica').value;
    let descricao = document.getElementById('descricaoDica').value;
    let user      = JSON.parse(localStorage.getItem('user'));
    let tipo      = document.getElementById('tipoDica').value;
    let img       = document.getElementById('imagensDica').value;

    if(titulo.length > 0 && descricao.length > 0 && user.id_user > 0){

        let formulario = document.getElementById('form-dica');
        let form = new FormData(formulario);
        let data1 = new Date();
        form.append('user', user.id_user);
        form.append('tipo', tipo);
        form.append('data', data1.toISOString());


        if(img.length == 0)
            form.append('imagem', 'false');
        else
            form.append('imagem', 'true');

        const response = await fetch('http://52.167.125.62:4567/cadastroDica', { method: "POST", body: form});
        const data = await response.json();

        window.location.href = "http://52.167.125.62:4567/dicas.html"; 
    }
    else{
        alert("campos não invalido ou não logado");
    }
}