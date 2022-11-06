import React from 'react'

export const productReducer = (state,action)=> {
  switch (action.type){
    case 'SORT_BY_PRICE':
        return {...state,sort: action.payload}
    case 'SORT_BY_COUNT':
        return {...state,sort: action.payload}
    case 'FILTER_BY_CATE1':
        return {...state,byCate1: !state.byCate1}
    case 'FILTER_BY_CATE2':
        return {...state,byCate2: !state.byCate2}
    case 'FILTER_BY_CATE3':
        return {...state,byCate3: !state.byCate3}
    case 'FILTER_BY_CATE4':
        return {...state,byCate4: !state.byCate4}
    case 'FILTER_BY_SEARCH':
        return {...state,searchQuery: action.payload}
    case 'CLEAR_FILTER':
        return {
            byCate1:false,
            byCate2:false,
            byCate3:false,
            byCate4:false,
            searchQuery:""
          }
    default:
        return state;
  }
}
