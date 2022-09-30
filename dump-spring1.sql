--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-06-17 17:39:31 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 200 (class 1259 OID 16413)
-- Name: users_login; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_login
(
    user_name character varying
);


ALTER TABLE public.users_login
    OWNER TO postgres;

--
-- TOC entry 3991 (class 0 OID 16413)
-- Dependencies: 200
-- Data for Name: users_login; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_login (user_name) FROM stdin;
first
second
\.


-- Completed on 2021-06-17 17:39:33 CEST

--
-- PostgreSQL database dump complete
--

