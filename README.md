## Realiza las siguientes consultas en xquery con el fichero premios.xml:
* (1 punto) Devuelve la frase "[nombre] ha ganado el premio de [categoria] en el año [año]"
```
let $premios := doc("premios.xml")//premio
return (
    for $premio in $premios
    return concat($premio/premiado, ' ha ganado el premio de ', $premio/@categoria, ' en el año ', $premio/año)
```

* (1 punto) Una tabla html [categoria] | [premiado] ordenada de mayor a menor por los [años]
```
<table>
  <thead>
    <tr>
      <th>categoria</th>
      <th>premiado</th>
    </tr>
  </thead>
  <tbody>
    {
      for $premio in doc("premios.xml")/premios_nobel/premios/premio
      let $categoria := $premio/@categoria
      let $premiado := $premio/premiado
      let $año := $premio/año
      order by xs:integer($año) descending
      return <tr>
               <td>{$categoria}</td>
               <td>{$premiado}</td>
             </tr>
    }
  </tbody>
</table>

```

* (2 punto) Incluir un nuevo premiado en un nuevo fichero
```
let $premios := doc("premios-nuevo.xml")//premios
let $nuevoPremiado :=
    <premio categoria="paz">
        <año>2022</año>
        <premiado>Annie Ernaux</premiado>
        <motivo>«Por el coraje y la agudeza clínica con la que descubre las raíces, los extrañamientos y las trabas colectivas de la memoria personal»</motivo>
    </premio>
return 
    (
    insert node $nuevoPremiado as last into $premios,
    $premios
    )
```

* (2 puntos) Realizar un fichero nuevo incluyendo motivos en los que no tienen

```
let $doc := doc("premios-nuevo-motivo.xml")
for $premio in $doc//premio[not(motivo)]
let $nuevoMotivo := "Nuevo motivo"
return if (exists($premio/motivo))
       then replace value of node $premio/motivo with $nuevoMotivo
       else insert node <motivo>{$nuevoMotivo}</motivo> into $premio
```

## Realiza una aplicación para usar el fichero employees.json (repositorio)
* (2 puntos) Que lea el fichero y guarde los datos en un array list
* (2 puntos) Despues de modificar algun datos en el array list que lo vuelva a guardar