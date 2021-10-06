package com.example.marvelhub.utils

interface Mapper<In , Out> {
   fun from(data :In): Out
   fun to (data :Out):In
}