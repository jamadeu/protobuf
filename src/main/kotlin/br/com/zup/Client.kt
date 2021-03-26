package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val request = FuncionarioRequest.newBuilder()
        .setNome("Jean")
        .setCpf("000.000.000-00")
        .setSalario(2000.20)
        .setIdade(36)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(
            FuncionarioRequest.Endereco.newBuilder()
                .setLogradouro("Logradouro")
                .setCep("00000-000")
                .setComplemento("Complemento")
                .build()
        )
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)

    println(response)
}