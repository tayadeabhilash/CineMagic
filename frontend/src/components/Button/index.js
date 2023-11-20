import React from "react";
import "./button.css";

function Button({ title, onClick, variant, disabled, fullWidth, type }) {
  let className = "bg-primary button";

  if (fullWidth) {
    className += "w-full";
  }

  if (variant === "outlined") {
    className = className.replace("bg-primary", "button-outlined");
  }

  return (
    <button
      className={className}
      type={type}
      onClick={onClick}
      disabled={disabled}
    >
      {title}
    </button>
  );
}

export default Button;
