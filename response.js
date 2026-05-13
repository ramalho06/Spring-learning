const func = async() =>{
    const response = await fetch('http://localhost:8080/users')
    const data = await response.json()
    console.log(data)
}

func()