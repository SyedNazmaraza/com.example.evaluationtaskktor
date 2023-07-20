package com.example.plugins

import com.example.data.model.Request
import com.example.data.model.Update
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configStatusPage(){
    install(StatusPages){
        exception<RequestValidationException>{
            call,cause -> call.respond(BaseResponse.ErrorResponse(ErrorCodes.INPUT_ERROR , mess = cause.reasons.toString()) )
        }

    }
}

fun Application.configRequestValidation(){
    install(RequestValidation){
        validate<Request>{
            if(it.query == "" || it.query==null){
                ValidationResult.Invalid("Give The Proper Query")
            }
            else{
                ValidationResult.Valid
            }
        }
        validate<Update>{
            if (it.id == null || it.id == 0){
                ValidationResult.Invalid("Id Should not be null or 0 ")
            }
            else{
                ValidationResult.Valid
            }
        }

    }

}