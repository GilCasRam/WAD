
var btn = document.getElementById('btn').addEventListener('click', ()=>{

    var tabla = document.getElementById("myTable");
        
    var categorias_tag = tabla.getElementsByClassName("categoria");
    var cant_tag = tabla.getElementsByClassName("cant");

    var categorias = [];
    var cantidad = []

    for(let i=0; i< categorias_tag.length; i++){
        categorias.push(categorias_tag.item(i).innerHTML);
        cantidad.push(cant_tag.item(i).innerHTML);
    }

    background = [];

    for(let i=0; i<categorias.length; i++){
        color = colorRGB();
        background.push(color);
    }


    var ctx = document.getElementById("myChart").getContext("2d");
    var mychart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: categorias,
            datasets: [{
                label: '# de productos',
                data: cantidad,
                backgroundColor: background
            }]
                
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    })

});


function generarNumero(numero){
	return (Math.random()*numero).toFixed(0);
}

function colorRGB(){
    var coolor = "("+generarNumero(255)+"," + generarNumero(255) + "," + generarNumero(255) +")";
    return "rgb" + coolor;
}
    




