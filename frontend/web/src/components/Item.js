import React from "react";

export default function Item(prop){
    return (
        <div className="item-card">
            <h3>{prop.name}</h3>
            <div className="info-group">
                <p>{prop.phone}</p>
            </div>
            <div className="info-group">
                <p>{prop.mail}</p>
            </div>
        </div>
    )
}