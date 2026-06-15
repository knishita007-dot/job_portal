const express = require('express');
const router = express.Router();
const searchService = require('../service/searchService');

router.post('/', async (req, res) => res.json(await searchService.semanticSearch(req.body.query, req.body.topK)));
router.get('/health', (req, res) => res.json({ status: 'ok' }));

module.exports = router;
